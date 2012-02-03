package eu.stratosphere.sopremo.type;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

import eu.stratosphere.pact.common.type.base.PactInteger;

public class IntNode extends NumericNode implements INumericNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4250062919293345310L;

	private transient PactInteger value;

	public IntNode() {
		this.value = new PactInteger();
	}

	public IntNode(final int v) {
		this.value = new PactInteger(v);
	}

	public static IntNode valueOf(final int v) {
		return new IntNode(v);
	}

	@Override
	public StringBuilder toString(final StringBuilder sb) {
		return sb.append(this.value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.value.hashCode();
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;

		final IntNode other = (IntNode) obj;
		if (!this.value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public void read(final DataInput in) throws IOException {
		this.value.read(in);
	}

	@Override
	public void write(final DataOutput out) throws IOException {
		this.value.write(out);
	}

	@Override
	public int getIntValue() {
		return this.value.getValue();
	}

	@Override
	public long getLongValue() {
		return Long.valueOf(this.value.getValue());
	}

	@Override
	public BigInteger getBigIntegerValue() {
		return BigInteger.valueOf(this.value.getValue());
	}

	@Override
	public BigDecimal getDecimalValue() {
		return BigDecimal.valueOf(this.value.getValue());
	}

	@Override
	public double getDoubleValue() {
		return Double.valueOf(this.value.getValue());
	}

	@Override
	public boolean isIntegralNumber() {
		return true;
	}

	@Override
	public String getValueAsText() {
		return this.value.toString();
	}

	@Override
	public Integer getJavaValue() {
		return this.value.getValue();
	}

	@Override
	public Type getType() {
		return Type.IntNode;
	}

	private void writeObject(final ObjectOutputStream out) throws IOException {
		out.writeInt(this.value.getValue());
	}

	private void readObject(final ObjectInputStream in) throws IOException {
		this.value = new PactInteger(in.readInt());
	}

	@Override
	public IntNode clone() {
		final IntNode clone = (IntNode) super.clone();
		clone.value = new PactInteger(this.value.getValue());
		return clone;
	}

	@Override
	public int compareToSameType(final IJsonNode other) {
		return this.value.getValue() - ((IntNode) other).value.getValue();
	}
}
