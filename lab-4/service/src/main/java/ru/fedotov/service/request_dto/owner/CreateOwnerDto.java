package ru.fedotov.service.request_dto.owner;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public class CreateOwnerDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private LocalDate birthdayDate;

    public CreateOwnerDto(@NotEmpty String name, @NotEmpty LocalDate birthdayDate) {
        this.name = name;
        this.birthdayDate = birthdayDate;
    }

    public @NotEmpty String getName() {
        return this.name;
    }

    public @NotEmpty LocalDate getBirthdayDate() {
        return this.birthdayDate;
    }

    public void setName(@NotEmpty String name) {
        this.name = name;
    }

    public void setBirthdayDate(@NotEmpty LocalDate birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CreateOwnerDto)) return false;
        final CreateOwnerDto other = (CreateOwnerDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$birthdayDate = this.getBirthdayDate();
        final Object other$birthdayDate = other.getBirthdayDate();
        if (this$birthdayDate == null ? other$birthdayDate != null : !this$birthdayDate.equals(other$birthdayDate))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CreateOwnerDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $birthdayDate = this.getBirthdayDate();
        result = result * PRIME + ($birthdayDate == null ? 43 : $birthdayDate.hashCode());
        return result;
    }

    public String toString() {
        return "CreateOwnerDto(name=" + this.getName() + ", birthdayDate=" + this.getBirthdayDate() + ")";
    }
}
