package me.kixstar.kixbungeebridge.rabbitmq;

import com.rabbitmq.client.AMQP;

public abstract class  Packet {

    private AMQP.BasicProperties props;

    public abstract byte[] serialize();

    public abstract void deserialize(byte[] raw);

    public AMQP.BasicProperties getProperties() {
        if(props == null) this.setProperties(new AMQP.BasicProperties().builder().build());
        return this.props;
    }

    public void setProperties(AMQP.BasicProperties props) {
        this.props = props;
    }
}
