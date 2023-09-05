package ru.fedotov.service.request_dto;

import java.time.LocalDate;

public class GetCatsByParametersDto {
    private String name;
    private LocalDate birthdayDate;
    private String breed;
    private String colour;

    public GetCatsByParametersDto() {
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

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof GetCatsByParametersDto)) return false;
        final GetCatsByParametersDto other = (GetCatsByParametersDto) o;
        if (!other.canEqual((Object) this)) return false;
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
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof GetCatsByParametersDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $birthdayDate = this.getBirthdayDate();
        result = result * PRIME + ($birthdayDate == null ? 43 : $birthdayDate.hashCode());
        final Object $breed = this.getBreed();
        result = result * PRIME + ($breed == null ? 43 : $breed.hashCode());
        final Object $colour = this.getColour();
        result = result * PRIME + ($colour == null ? 43 : $colour.hashCode());
        return result;
    }

    public String toString() {
        return "GetCatsByParametersDto(name=" + this.getName() + ", birthdayDate=" + this.getBirthdayDate() + ", breed=" + this.getBreed() + ", colour=" + this.getColour() + ")";
    }
}
