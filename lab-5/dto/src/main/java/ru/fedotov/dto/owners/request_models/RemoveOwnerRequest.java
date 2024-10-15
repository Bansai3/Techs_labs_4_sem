package ru.fedotov.dto.owners.request_models;

import jakarta.validation.constraints.Positive;

public class RemoveOwnerRequest {
    @Positive(message = "Id must be positive!")
    private Long ownerId;

    public RemoveOwnerRequest() {
    }


    public @Positive(message = "Id must be positive!") Long getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(@Positive(message = "Id must be positive!") Long ownerId) {
        this.ownerId = ownerId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RemoveOwnerRequest)) return false;
        final RemoveOwnerRequest other = (RemoveOwnerRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$ownerId = this.getOwnerId();
        final Object other$ownerId = other.getOwnerId();
        if (this$ownerId == null ? other$ownerId != null : !this$ownerId.equals(other$ownerId)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RemoveOwnerRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $ownerId = this.getOwnerId();
        result = result * PRIME + ($ownerId == null ? 43 : $ownerId.hashCode());
        return result;
    }

    public String toString() {
        return "RemoveOwnerRequest(ownerId=" + this.getOwnerId() + ")";
    }
}
