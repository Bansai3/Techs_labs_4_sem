package request_dto;

public class RemoveCatDto {
    private long catId;

    public RemoveCatDto(long catId) {
        this.catId = catId;
    }

    public long getCatId() {
        return this.catId;
    }

    public void setCatId(long catId) {
        this.catId = catId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RemoveCatDto)) return false;
        final RemoveCatDto other = (RemoveCatDto) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getCatId() != other.getCatId()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RemoveCatDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $catId = this.getCatId();
        result = result * PRIME + (int) ($catId >>> 32 ^ $catId);
        return result;
    }

    public String toString() {
        return "RemoveCat(catId=" + this.getCatId() + ")";
    }
}
