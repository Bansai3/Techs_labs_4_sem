package ru.fedotov.service.request_dto;

import lombok.NonNull;

import java.time.LocalDate;

public class CreateCatDto {
    @NonNull
    private String name;
    @NonNull
    private LocalDate birthdayDate;
    @NonNull
    private String breed;
    @NonNull
    private String colour;

    public CreateCatDto(@NonNull String name, @NonNull LocalDate birthdayDate, @NonNull String breed, @NonNull String colour) {
        this.name = name;
        this.birthdayDate = birthdayDate;
        this.breed = breed;
        this.colour = colour;
    }

    public @NonNull String getName() {
        return this.name;
    }

    public @NonNull LocalDate getBirthdayDate() {
        return this.birthdayDate;
    }

    public @NonNull String getBreed() {
        return this.breed;
    }

    public @NonNull String getColour() {
        return this.colour;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setBirthdayDate(@NonNull LocalDate birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public void setBreed(@NonNull String breed) {
        this.breed = breed;
    }

    public void setColour(@NonNull String colour) {
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
