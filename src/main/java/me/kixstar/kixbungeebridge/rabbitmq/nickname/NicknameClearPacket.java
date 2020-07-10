package me.kixstar.kixbungeebridge.rabbitmq.nickname;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.kixstar.kixbungeebridge.rabbitmq.Packet;

public class NicknameClearPacket implements Packet {

    private String playerUUID;

    //no args constructor is required for deserialization of packets
    public NicknameClearPacket() {}

    public NicknameClearPacket(String playerUUID) {
        this.playerUUID = playerUUID;
    }

    @Override
    public byte[] serialize() {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(this.playerUUID);

        return out.toByteArray();
    }

    @Override
    public NicknameClearPacket deserialize(byte[] raw) {
        ByteArrayDataInput in  = ByteStreams.newDataInput(raw);
        return new NicknameClearPacket(
                in.readUTF()
        );

    }
}
