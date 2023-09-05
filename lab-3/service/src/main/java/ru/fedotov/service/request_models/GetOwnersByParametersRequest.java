package ru.fedotov.service.request_models;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class GetOwnersByParametersRequest {
    private String name;
    @Past(message = "Birthday date must be in the past!")
    private LocalDate birthDate;

    public GetOwnersByParametersRequest() {
    }


    public @NotEmpty(message = "Name should not be empty!") @Size(min = 2, max = 50, message = "Name length should be between 2 and 50!") String getName() {
        return this.name;
    }

    public @Past(message = "Birthday date must be in the past!") LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setName(@NotEmpty(message = "Name should not be empty!") @Size(min = 2, max = 50, message = "Name length should be between 2 and 50!") String name) {
        this.name = name;
    }

    public void setBirthDate(@Past(message = "Birthday date must be in the past!") LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof GetOwnersByParametersRequest)) return false;
        final GetOwnersByParametersRequest other = (GetOwnersByParametersRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$birthDate = this.getBirthDate();
        final Object other$birthDate = other.getBirthDate();
        if (this$birthDate == null ? other$birthDate != null : !this$birthDate.equals(other$birthDate)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof GetOwnersByParametersRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $birthDate = this.getBirthDate();
        result = result * PRIME + ($birthDate == null ? 43 : $birthDate.hashCode());
        return result;
    }

    public String toString() {
        return "GetOwnersByParametersRequest(name=" + this.getName() + ", birthDate=" + this.getBirthDate() + ")";
    }
}
