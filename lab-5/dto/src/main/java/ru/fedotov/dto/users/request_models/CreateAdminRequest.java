package ru.fedotov.dto.users.request_models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CreateAdminRequest {
    @NotEmpty
    @Size(min = 3, max = 50, message = "User name length must be >= 3 and <= 50!")
    private String userName;
    @NotEmpty
    @Size(min = 3, max = 100, message = "Password length must be >= 3 and <= 100!")
    private String password;

    public CreateAdminRequest() {
    }

    public @NotEmpty @Size(min = 3, max = 50, message = "User name length must be >= 3 and <= 50!") String getUserName() {
        return this.userName;
    }

    public @NotEmpty @Size(min = 3, max = 100, message = "Password length must be >= 3 and <= 100!") String getPassword() {
        return this.password;
    }

    public void setUserName(@NotEmpty @Size(min = 3, max = 50, message = "User name length must be >= 3 and <= 50!") String userName) {
        this.userName = userName;
    }

    public void setPassword(@NotEmpty @Size(min = 3, max = 100, message = "Password length must be >= 3 and <= 100!") String password) {
        this.password = password;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CreateAdminRequest)) return false;
        final CreateAdminRequest other = (CreateAdminRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$userName = this.getUserName();
        final Object other$userName = other.getUserName();
        if (this$userName == null ? other$userName != null : !this$userName.equals(other$userName)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CreateAdminRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $userName = this.getUserName();
        result = result * PRIME + ($userName == null ? 43 : $userName.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        return result;
    }

    public String toString() {
        return "CreateAdminRequest(userName=" + this.getUserName() + ", password=" + this.getPassword() + ")";
    }
}
