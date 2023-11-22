import java.util.Arrays;

public class BitVector {
    private int[] vector;
    private int size;
    public BitVector() {
        vector = new int[size];
    }

    public BitVector(int size) {
        assert size > 0: "The size can't be negative";
        this.size = size;
        vector = new int[size];
    }

    public void setBit(int position) {
        assert 0 <= position && position < size: "Position out of bounds";
        vector[position] = 1;
    }

    public void clearBit(int position) {
        assert 0 <= position && position < size: "Position out of bounds";
        vector[position] = 0;
    }

    public boolean testBit(int position) {
        assert 0 <= position && position < size: "Position out of bounds";
        return vector[position] == 1;
    }

    public void setAll() {
        assert size >= 0;
        for (int i = 0; i < size; ++i) vector[i] = 1;
    }

    public void clearAll() {
        assert size >= 0;
        for (int i = 0; i < size; ++i) vector[i] = 0;
    }

    public BitVector and(BitVector other) {
        assert other.size == this.size : "The operation cannot be applied";
        BitVector result = new BitVector(size);
        for (int i = 0; i< size; ++i)
            result.vector[i] = this.vector[i] & result.vector[i];
        return result;
    }

    public BitVector or(BitVector other) {
        assert other.size == this.size : "The operation cannot be applied";
        BitVector result = new BitVector(size);
        for (int i = 0; i< size; ++i)
            result.vector[i] = this.vector[i] | result.vector[i];
        return result;
    }

    public BitVector xor(BitVector other) {
        assert other.size == this.size : "The operation cannot be applied";
        BitVector result = new BitVector(size);
        for (int i = 0; i< size; ++i)
            result.vector[i] = this.vector[i] ^ result.vector[i];
        return result;
    }

    public BitVector negate() {
        BitVector result = new BitVector(size);
        for (int i = 0; i < size; ++i)
            result.vector[i] = ~this.vector[i];
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(vector);
    }
}
