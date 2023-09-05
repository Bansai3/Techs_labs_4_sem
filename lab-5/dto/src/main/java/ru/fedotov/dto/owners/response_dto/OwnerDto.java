package ru.fedotov.dto.owners.response_dto;

import java.time.LocalDate;
import java.util.Set;

public class OwnerDto {
    private long id;

    private String name;

    private LocalDate birthdayDate;

    private Set<Long> catsIds;

    public OwnerDto() {
    }


    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public LocalDate getBirthdayDate() {
        return this.birthdayDate;
    }

    public Set<Long> getCatsIds() {
        return this.catsIds;
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

    public void setCatsIds(Set<Long> catsIds) {
        this.catsIds = catsIds;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof OwnerDto)) return false;
        final OwnerDto other = (OwnerDto) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$birthdayDate = this.getBirthdayDate();
        final Object other$birthdayDate = other.getBirthdayDate();
        if (this$birthdayDate == null ? other$birthdayDate != null : !this$birthdayDate.equals(other$birthdayDate))
            return false;
        final Object this$catsIds = this.getCatsIds();
        final Object other$catsIds = other.getCatsIds();
        if (this$catsIds == null ? other$catsIds != null : !this$catsIds.equals(other$catsIds)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OwnerDto;
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
        final Object $catsIds = this.getCatsIds();
        result = result * PRIME + ($catsIds == null ? 43 : $catsIds.hashCode());
        return result;
    }

    public String toString() {
        return "OwnerDto(id=" + this.getId() + ", name=" + this.getName() + ", birthdayDate=" + this.getBirthdayDate() + ", catsIds=" + this.getCatsIds() + ")";
    }
}
