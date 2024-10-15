package request_dto;

import lombok.NonNull;

import java.time.LocalDate;

public class CreateOwnerDto {
    @NonNull
    private String name;
    @NonNull
    private LocalDate birthDate;

    public CreateOwnerDto(@NonNull String name, @NonNull LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public @NonNull String getName() {
        return this.name;
    }

    public @NonNull LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setBirthDate(@NonNull LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CreateOwnerDto)) return false;
        final CreateOwnerDto other = (CreateOwnerDto) o;
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
        return other instanceof CreateOwnerDto;
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
        return "CreateOwner(name=" + this.getName() + ", birthDate=" + this.getBirthDate() + ")";
    }
}
