/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package ragna.cqrs.query.model;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class OrderedItem extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -5587080994169691967L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"OrderedItem\",\"namespace\":\"ragna.cqrs.query.model\",\"fields\":[{\"name\":\"customerId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"label\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"price\",\"type\":\"double\"},{\"name\":\"quantity\",\"type\":\"int\",\"default\":1,\"order\":\"ignore\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<OrderedItem> ENCODER =
      new BinaryMessageEncoder<OrderedItem>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<OrderedItem> DECODER =
      new BinaryMessageDecoder<OrderedItem>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<OrderedItem> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<OrderedItem> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<OrderedItem> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<OrderedItem>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this OrderedItem to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a OrderedItem from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a OrderedItem instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static OrderedItem fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.lang.String customerId;
   private java.lang.String label;
   private double price;
   private int quantity;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public OrderedItem() {}

  /**
   * All-args constructor.
   * @param customerId The new value for customerId
   * @param label The new value for label
   * @param price The new value for price
   * @param quantity The new value for quantity
   */
  public OrderedItem(java.lang.String customerId, java.lang.String label, java.lang.Double price, java.lang.Integer quantity) {
    this.customerId = customerId;
    this.label = label;
    this.price = price;
    this.quantity = quantity;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return customerId;
    case 1: return label;
    case 2: return price;
    case 3: return quantity;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: customerId = value$ != null ? value$.toString() : null; break;
    case 1: label = value$ != null ? value$.toString() : null; break;
    case 2: price = (java.lang.Double)value$; break;
    case 3: quantity = (java.lang.Integer)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'customerId' field.
   * @return The value of the 'customerId' field.
   */
  public java.lang.String getCustomerId() {
    return customerId;
  }


  /**
   * Sets the value of the 'customerId' field.
   * @param value the value to set.
   */
  public void setCustomerId(java.lang.String value) {
    this.customerId = value;
  }

  /**
   * Gets the value of the 'label' field.
   * @return The value of the 'label' field.
   */
  public java.lang.String getLabel() {
    return label;
  }


  /**
   * Sets the value of the 'label' field.
   * @param value the value to set.
   */
  public void setLabel(java.lang.String value) {
    this.label = value;
  }

  /**
   * Gets the value of the 'price' field.
   * @return The value of the 'price' field.
   */
  public double getPrice() {
    return price;
  }


  /**
   * Sets the value of the 'price' field.
   * @param value the value to set.
   */
  public void setPrice(double value) {
    this.price = value;
  }

  /**
   * Gets the value of the 'quantity' field.
   * @return The value of the 'quantity' field.
   */
  public int getQuantity() {
    return quantity;
  }


  /**
   * Sets the value of the 'quantity' field.
   * @param value the value to set.
   */
  public void setQuantity(int value) {
    this.quantity = value;
  }

  /**
   * Creates a new OrderedItem RecordBuilder.
   * @return A new OrderedItem RecordBuilder
   */
  public static ragna.cqrs.query.model.OrderedItem.Builder newBuilder() {
    return new ragna.cqrs.query.model.OrderedItem.Builder();
  }

  /**
   * Creates a new OrderedItem RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new OrderedItem RecordBuilder
   */
  public static ragna.cqrs.query.model.OrderedItem.Builder newBuilder(ragna.cqrs.query.model.OrderedItem.Builder other) {
    if (other == null) {
      return new ragna.cqrs.query.model.OrderedItem.Builder();
    } else {
      return new ragna.cqrs.query.model.OrderedItem.Builder(other);
    }
  }

  /**
   * Creates a new OrderedItem RecordBuilder by copying an existing OrderedItem instance.
   * @param other The existing instance to copy.
   * @return A new OrderedItem RecordBuilder
   */
  public static ragna.cqrs.query.model.OrderedItem.Builder newBuilder(ragna.cqrs.query.model.OrderedItem other) {
    if (other == null) {
      return new ragna.cqrs.query.model.OrderedItem.Builder();
    } else {
      return new ragna.cqrs.query.model.OrderedItem.Builder(other);
    }
  }

  /**
   * RecordBuilder for OrderedItem instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<OrderedItem>
    implements org.apache.avro.data.RecordBuilder<OrderedItem> {

    private java.lang.String customerId;
    private java.lang.String label;
    private double price;
    private int quantity;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(ragna.cqrs.query.model.OrderedItem.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.customerId)) {
        this.customerId = data().deepCopy(fields()[0].schema(), other.customerId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.label)) {
        this.label = data().deepCopy(fields()[1].schema(), other.label);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.price)) {
        this.price = data().deepCopy(fields()[2].schema(), other.price);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.quantity)) {
        this.quantity = data().deepCopy(fields()[3].schema(), other.quantity);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
    }

    /**
     * Creates a Builder by copying an existing OrderedItem instance
     * @param other The existing instance to copy.
     */
    private Builder(ragna.cqrs.query.model.OrderedItem other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.customerId)) {
        this.customerId = data().deepCopy(fields()[0].schema(), other.customerId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.label)) {
        this.label = data().deepCopy(fields()[1].schema(), other.label);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.price)) {
        this.price = data().deepCopy(fields()[2].schema(), other.price);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.quantity)) {
        this.quantity = data().deepCopy(fields()[3].schema(), other.quantity);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'customerId' field.
      * @return The value.
      */
    public java.lang.String getCustomerId() {
      return customerId;
    }


    /**
      * Sets the value of the 'customerId' field.
      * @param value The value of 'customerId'.
      * @return This builder.
      */
    public ragna.cqrs.query.model.OrderedItem.Builder setCustomerId(java.lang.String value) {
      validate(fields()[0], value);
      this.customerId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'customerId' field has been set.
      * @return True if the 'customerId' field has been set, false otherwise.
      */
    public boolean hasCustomerId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'customerId' field.
      * @return This builder.
      */
    public ragna.cqrs.query.model.OrderedItem.Builder clearCustomerId() {
      customerId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'label' field.
      * @return The value.
      */
    public java.lang.String getLabel() {
      return label;
    }


    /**
      * Sets the value of the 'label' field.
      * @param value The value of 'label'.
      * @return This builder.
      */
    public ragna.cqrs.query.model.OrderedItem.Builder setLabel(java.lang.String value) {
      validate(fields()[1], value);
      this.label = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'label' field has been set.
      * @return True if the 'label' field has been set, false otherwise.
      */
    public boolean hasLabel() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'label' field.
      * @return This builder.
      */
    public ragna.cqrs.query.model.OrderedItem.Builder clearLabel() {
      label = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'price' field.
      * @return The value.
      */
    public double getPrice() {
      return price;
    }


    /**
      * Sets the value of the 'price' field.
      * @param value The value of 'price'.
      * @return This builder.
      */
    public ragna.cqrs.query.model.OrderedItem.Builder setPrice(double value) {
      validate(fields()[2], value);
      this.price = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'price' field has been set.
      * @return True if the 'price' field has been set, false otherwise.
      */
    public boolean hasPrice() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'price' field.
      * @return This builder.
      */
    public ragna.cqrs.query.model.OrderedItem.Builder clearPrice() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'quantity' field.
      * @return The value.
      */
    public int getQuantity() {
      return quantity;
    }


    /**
      * Sets the value of the 'quantity' field.
      * @param value The value of 'quantity'.
      * @return This builder.
      */
    public ragna.cqrs.query.model.OrderedItem.Builder setQuantity(int value) {
      validate(fields()[3], value);
      this.quantity = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'quantity' field has been set.
      * @return True if the 'quantity' field has been set, false otherwise.
      */
    public boolean hasQuantity() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'quantity' field.
      * @return This builder.
      */
    public ragna.cqrs.query.model.OrderedItem.Builder clearQuantity() {
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public OrderedItem build() {
      try {
        OrderedItem record = new OrderedItem();
        record.customerId = fieldSetFlags()[0] ? this.customerId : (java.lang.String) defaultValue(fields()[0]);
        record.label = fieldSetFlags()[1] ? this.label : (java.lang.String) defaultValue(fields()[1]);
        record.price = fieldSetFlags()[2] ? this.price : (java.lang.Double) defaultValue(fields()[2]);
        record.quantity = fieldSetFlags()[3] ? this.quantity : (java.lang.Integer) defaultValue(fields()[3]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<OrderedItem>
    WRITER$ = (org.apache.avro.io.DatumWriter<OrderedItem>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<OrderedItem>
    READER$ = (org.apache.avro.io.DatumReader<OrderedItem>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.customerId);

    out.writeString(this.label);

    out.writeDouble(this.price);

    out.writeInt(this.quantity);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.customerId = in.readString();

      this.label = in.readString();

      this.price = in.readDouble();

      this.quantity = in.readInt();

    } else {
      for (int i = 0; i < 4; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.customerId = in.readString();
          break;

        case 1:
          this.label = in.readString();
          break;

        case 2:
          this.price = in.readDouble();
          break;

        case 3:
          this.quantity = in.readInt();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










