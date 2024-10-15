package ru.fedotov.service.request_dto.cat;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public class CreateCatDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private LocalDate birthdayDate;
    @NotEmpty
    private String breed;
    @NotEmpty
    private String colour;

    public CreateCatDto(@NotEmpty String name, @NotEmpty LocalDate birthdayDate, @NotEmpty String breed, @NotEmpty String colour) {
        this.name = name;
        this.birthdayDate = birthdayDate;
        this.breed = breed;
        this.colour = colour;
    }

    public @NotEmpty String getName() {
        return this.name;
    }

    public @NotEmpty LocalDate getBirthdayDate() {
        return this.birthdayDate;
    }

    public @NotEmpty String getBreed() {
        return this.breed;
    }

    public @NotEmpty String getColour() {
        return this.colour;
    }

    public void setName(@NotEmpty String name) {
        this.name = name;
    }

    public void setBirthdayDate(@NotEmpty LocalDate birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public void setBreed(@NotEmpty String breed) {
        this.breed = breed;
    }

    public void setColour(@NotEmpty String colour) {
        this.colour = colour;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CreateCatDto)) return false;
        final CreateCatDto other = (CreateCatDto) o;
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
        return other instanceof CreateCatDto;
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
        return "CreateCatDto(name=" + this.getName() + ", birthdayDate=" + this.getBirthdayDate() + ", breed=" + this.getBreed() + ", colour=" + this.getColour() + ")";
    }
}
