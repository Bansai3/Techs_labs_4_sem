package ru.fedotov.service.response_dto;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
public class CatDto {
    private long id;
    private String name;
    private LocalDate birthdayDate;
    private String breed;
    private String colour;
    private Long ownerId;
    private Set<Long> friendCatsIds;

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public LocalDate getBirthdayDate() {
        return this.birthdayDate;
    }

    public String getBreed() {
        return this.breed;
    }

    public String getColour() {
        return this.colour;
    }

    public Long getOwnerId() {
        return this.ownerId;
    }

    public Set<Long> getFriendCatsIds() {
        return this.friendCatsIds;
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

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public void setFriendCatsIds(Set<Long> friendCatsIds) {
        this.friendCatsIds = friendCatsIds;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CatDto)) return false;
        final CatDto other = (CatDto) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$birthdayDate = this.getBirthdayDate();
        final Object other$birthdayDate = other.getBirthdayDate();
        if (this$birthdayDate == null ? other$birthdayDate != null : !this$birthdayDate.equals(other$birthdayDate))
            return false;
        final Object this$breed = this.getBreed();
        final Object other$breed = other.getBreed();
        if (this$breed == null ? other$breed != null : !this$breed.equals(other$breed)) return false;
        final Object this$colour = this.getColour();
        final Object other$colour = other.getColour();
        if (this$colour == null ? other$colour != null : !this$colour.equals(other$colour)) return false;
        final Object this$ownerId = this.getOwnerId();
        final Object other$ownerId = other.getOwnerId();
        if (this$ownerId == null ? other$ownerId != null : !this$ownerId.equals(other$ownerId)) return false;
        final Object this$friendCatsIds = this.getFriendCatsIds();
        final Object other$friendCatsIds = other.getFriendCatsIds();
        if (this$friendCatsIds == null ? other$friendCatsIds != null : !this$friendCatsIds.equals(other$friendCatsIds))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CatDto;
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
        final Object $breed = this.getBreed();
        result = result * PRIME + ($breed == null ? 43 : $breed.hashCode());
        final Object $colour = this.getColour();
        result = result * PRIME + ($colour == null ? 43 : $colour.hashCode());
        final Object $ownerId = this.getOwnerId();
        result = result * PRIME + ($ownerId == null ? 43 : $ownerId.hashCode());
        final Object $friendCatsIds = this.getFriendCatsIds();
        result = result * PRIME + ($friendCatsIds == null ? 43 : $friendCatsIds.hashCode());
        return result;
    }

    public String toString() {
        return "CatDto(id=" + this.getId() + ", name=" + this.getName() + ", birthdayDate=" + this.getBirthdayDate() + ", breed=" + this.getBreed() + ", colour=" + this.getColour() + ", ownerId=" + this.getOwnerId() + ", friendCatsIds=" + this.getFriendCatsIds() + ")";
    }
}
