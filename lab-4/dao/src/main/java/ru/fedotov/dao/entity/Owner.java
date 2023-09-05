package ru.fedotov.dao.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "Owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Birthday date")
    private LocalDate birthdayDate;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(
            name = "Cats_Owners",
            joinColumns = @JoinColumn(name = "Owner_id"),
            inverseJoinColumns = @JoinColumn(name = "Cat_id")
    )
    private Set<Cat> cats;
    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
    private User user;

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public LocalDate getBirthdayDate() {
        return this.birthdayDate;
    }

    public Set<Cat> getCats() {
        return this.cats;
    }

    public User getUser() {
        return this.user;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdayDate(LocalDate birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public void setCats(Set<Cat> cats) {
        this.cats = cats;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Owner)) return false;
        final Owner other = (Owner) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$birthdayDate = this.getBirthdayDate();
        final Object other$birthdayDate = other.getBirthdayDate();
        if (this$birthdayDate == null ? other$birthdayDate != null : !this$birthdayDate.equals(other$birthdayDate))
            return false;
        final Object this$cats = this.getCats();
        final Object other$cats = other.getCats();
        if (this$cats == null ? other$cats != null : !this$cats.equals(other$cats)) return false;
        final Object this$user = this.getUser();
        final Object other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Owner;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $id = this.getId();
        result = result * PRIME + (int) ($id >>> 32 ^ $id);
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $birthdayDate = this.getBirthdayDate();
        result = result * PRIME + ($birthdayDate == null ? 43 : $birthdayDate.hashCode());
        final Object $cats = this.getCats();
        result = result * PRIME + ($cats == null ? 43 : $cats.hashCode());
        final Object $user = this.getUser();
        result = result * PRIME + ($user == null ? 43 : $user.hashCode());
        return result;
    }

    public String toString() {
        return "Owner(id=" + this.getId() + ", name=" + this.getName() + ", birthdayDate=" + this.getBirthdayDate() + ", cats=" + this.getCats() + ", user=" + this.getUser() + ")";
    }
}