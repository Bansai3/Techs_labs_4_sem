package request_dto;

public class AdoptCatDto {

    private long catId;
    private long ownerId;

    public AdoptCatDto(long catId, long ownerId) {
        this.catId = catId;
        this.ownerId = ownerId;
    }

    public long getCatId() {
        return this.catId;
    }

    public long getOwnerId() {
        return this.ownerId;
    }

    public void setCatId(long catId) {
        this.catId = catId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AdoptCatDto)) return false;
        final AdoptCatDto other = (AdoptCatDto) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getCatId() != other.getCatId()) return false;
        if (this.getOwnerId() != other.getOwnerId()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AdoptCatDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $catId = this.getCatId();
        result = result * PRIME + (int) ($catId >>> 32 ^ $catId);
        final long $ownerId = this.getOwnerId();
        result = result * PRIME + (int) ($ownerId >>> 32 ^ $ownerId);
        return result;
    }

    public String toString() {
        return "AdoptCat(catId=" + this.getCatId() + ", ownerId=" + this.getOwnerId() + ")";
    }
}

