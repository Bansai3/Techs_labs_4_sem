package ru.fedotov.service.request_models;

import jakarta.validation.constraints.Positive;

public class AdoptCatRequest {
    @Positive(message = "Id must be positive!")
    private Long catId;
    @Positive(message = "Id must be positive!")
    private Long ownerId;

    public AdoptCatRequest() {
    }


    public @Positive(message = "Id must be positive!") Long getCatId() {
        return this.catId;
    }

    public @Positive(message = "Id must be positive!") Long getOwnerId() {
        return this.ownerId;
    }

    public void setCatId(@Positive(message = "Id must be positive!") Long catId) {
        this.catId = catId;
    }

    public void setOwnerId(@Positive(message = "Id must be positive!") Long ownerId) {
        this.ownerId = ownerId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AdoptCatRequest)) return false;
        final AdoptCatRequest other = (AdoptCatRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$catId = this.getCatId();
        final Object other$catId = other.getCatId();
        if (this$catId == null ? other$catId != null : !this$catId.equals(other$catId)) return false;
        final Object this$ownerId = this.getOwnerId();
        final Object other$ownerId = other.getOwnerId();
        if (this$ownerId == null ? other$ownerId != null : !this$ownerId.equals(other$ownerId)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AdoptCatRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $catId = this.getCatId();
        result = result * PRIME + ($catId == null ? 43 : $catId.hashCode());
        final Object $ownerId = this.getOwnerId();
        result = result * PRIME + ($ownerId == null ? 43 : $ownerId.hashCode());
        return result;
    }

    public String toString() {
        return "AdoptCatRequest(catId=" + this.getCatId() + ", ownerId=" + this.getOwnerId() + ")";
    }
}
