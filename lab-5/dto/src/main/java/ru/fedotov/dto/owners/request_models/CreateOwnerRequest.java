package ru.fedotov.dto.owners.request_models;

import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public class CreateOwnerRequest {
    private String name;
    @Past(message = "Birthday date must be in the past!")
    private LocalDate birthdayDate;

    public CreateOwnerRequest() {
    }


    public String getName() {
        return this.name;
    }

    public @Past(message = "Birthday date must be in the past!") LocalDate getBirthdayDate() {
        return this.birthdayDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdayDate(@Past(message = "Birthday date must be in the past!") LocalDate birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CreateOwnerRequest)) return false;
        final CreateOwnerRequest other = (CreateOwnerRequest) o;
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
        return other instanceof CreateOwnerRequest;
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
        return "CreateOwnerRequest(name=" + this.getName() + ", birthdayDate=" + this.getBirthdayDate() + ")";
    }
}
