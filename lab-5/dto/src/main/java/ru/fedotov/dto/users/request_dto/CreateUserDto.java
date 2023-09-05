package ru.fedotov.dto.users.request_dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import ru.fedotov.jpa.users.role.Role;
import ru.fedotov.jpa.users.status.Status;

public class CreateUserDto {
    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;
    @Positive
    private Long ownerId;
    private Status status;
    private Role role;

    public CreateUserDto() {
    }

    public @NotEmpty String getUserName() {
        return this.userName;
    }

    public @NotEmpty String getPassword() {
        return this.password;
    }

    public @Positive Long getOwnerId() {
        return this.ownerId;
    }

    public Status getStatus() {
        return this.status;
    }

    public Role getRole() {
        return this.role;
    }

    public void setUserName(@NotEmpty String userName) {
        this.userName = userName;
    }

    public void setPassword(@NotEmpty String password) {
        this.password = password;
    }

    public void setOwnerId(@Positive Long ownerId) {
        this.ownerId = ownerId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CreateUserDto)) return false;
        final CreateUserDto other = (CreateUserDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$userName = this.getUserName();
        final Object other$userName = other.getUserName();
        if (this$userName == null ? other$userName != null : !this$userName.equals(other$userName)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$ownerId = this.getOwnerId();
        final Object other$ownerId = other.getOwnerId();
        if (this$ownerId == null ? other$ownerId != null : !this$ownerId.equals(other$ownerId)) return false;
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final Object this$role = this.getRole();
        final Object other$role = other.getRole();
        if (this$role == null ? other$role != null : !this$role.equals(other$role)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CreateUserDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $userName = this.getUserName();
        result = result * PRIME + ($userName == null ? 43 : $userName.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $ownerId = this.getOwnerId();
        result = result * PRIME + ($ownerId == null ? 43 : $ownerId.hashCode());
        final Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final Object $role = this.getRole();
        result = result * PRIME + ($role == null ? 43 : $role.hashCode());
        return result;
    }

    public String toString() {
        return "CreateUserDto(userName=" + this.getUserName() + ", password=" + this.getPassword() + ", ownerId=" + this.getOwnerId() + ", status=" + this.getStatus() + ", role=" + this.getRole() + ")";
    }
}
