package ru.fedotov.dto.cats.request_dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
public class UpdateCatDto {

    @NotEmpty
    private String name;
    @NotEmpty
    private LocalDate birthdayDate;
    @NotEmpty
    private String breed;
    @NotEmpty
    private String colour;
    @Positive
    private Long id;

    public UpdateCatDto() {
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

    public @Positive Long getId() {
        return this.id;
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

    public void setId(@Positive Long id) {
        this.id = id;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UpdateCatDto)) return false;
        final UpdateCatDto other = (UpdateCatDto) o;
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
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UpdateCatDto;
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
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }

    public String toString() {
        return "UpdateCatRequest(name=" + this.getName() + ", birthdayDate=" + this.getBirthdayDate() + ", breed=" + this.getBreed() + ", colour=" + this.getColour() + ", id=" + this.getId() + ")";
    }
}