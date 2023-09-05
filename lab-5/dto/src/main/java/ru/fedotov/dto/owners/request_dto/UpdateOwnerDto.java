package ru.fedotov.dto.owners.request_dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class UpdateOwnerDto {

    @NotEmpty
    private String name;
    @NotEmpty
    private LocalDate birthdayDate;
    @Positive
    private Long id;

    public UpdateOwnerDto() {
    }

    public @NotEmpty String getName() {
        return this.name;
    }

    public @NotEmpty LocalDate getBirthdayDate() {
        return this.birthdayDate;
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

    public void setId(@Positive Long id) {
        this.id = id;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UpdateOwnerDto)) return false;
        final UpdateOwnerDto other = (UpdateOwnerDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$birthdayDate = this.getBirthdayDate();
        final Object other$birthdayDate = other.getBirthdayDate();
        if (this$birthdayDate == null ? other$birthdayDate != null : !this$birthdayDate.equals(other$birthdayDate))
            return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UpdateOwnerDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $birthdayDate = this.getBirthdayDate();
        result = result * PRIME + ($birthdayDate == null ? 43 : $birthdayDate.hashCode());
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }

    public String toString() {
        return "UpdateOwnerDto(name=" + this.getName() + ", birthdayDate=" + this.getBirthdayDate() + ", id=" + this.getId() + ")";
    }
}
