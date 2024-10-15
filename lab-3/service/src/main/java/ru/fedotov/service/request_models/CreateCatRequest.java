package ru.fedotov.service.request_models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class CreateCatRequest {
   @NotEmpty(message = "Name should not be empty!")
   @Size(min = 2, max = 50, message = "Name length should be between 2 and 50!")
    private String name;
    @Past(message = "Birthday date must be in the past!")
    private LocalDate birthdayDate;
    @NotEmpty(message = "Breed should not be empty!")
    private String breed;
    @NotEmpty(message = "Colour should not be empty!")
    private String colour;

    public CreateCatRequest() {
    }


    public @NotEmpty(message = "Name should not be empty!") @Size(min = 2, max = 50, message = "Name length should be between 2 and 50!") String getName() {
        return this.name;
    }

    public @Past(message = "Birthday date must be in the past!") LocalDate getBirthdayDate() {
        return this.birthdayDate;
    }

    public @NotEmpty(message = "Breed should not be empty!") String getBreed() {
        return this.breed;
    }

    public @NotEmpty(message = "Colour should not be empty!") String getColour() {
        return this.colour;
    }

    public void setName(@NotEmpty(message = "Name should not be empty!") @Size(min = 2, max = 50, message = "Name length should be between 2 and 50!") String name) {
        this.name = name;
    }

    public void setBirthdayDate(@Past(message = "Birthday date must be in the past!") LocalDate birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public void setBreed(@NotEmpty(message = "Breed should not be empty!") String breed) {
        this.breed = breed;
    }

    public void setColour(@NotEmpty(message = "Colour should not be empty!") String colour) {
        this.colour = colour;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CreateCatRequest)) return false;
        final CreateCatRequest other = (CreateCatRequest) o;
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
        return other instanceof CreateCatRequest;
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
        return "CreateCatRequest(name=" + this.getName() + ", birthdayDate=" + this.getBirthdayDate() + ", breed=" + this.getBreed() + ", colour=" + this.getColour() + ")";
    }
}
