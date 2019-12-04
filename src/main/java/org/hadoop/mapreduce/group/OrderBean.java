package org.hadoop.mapreduce.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderBean implements WritableComparable<OrderBean> {
    private String orderId;
    private String pId;
    private double price;

    @Override
    public int compareTo(OrderBean o) {
        //先按订单排序，再按价格降序，再按商品降序
        int i = this.orderId.compareTo(o.getOrderId());
        if(i!=0){
            return i;
        }
        double d = o.price - this.price;
        if(d==0){
            return o.getPId().compareTo(this.getPId());
        }else if(d>0){
            return 1;
        }else{
            return -1;
        }
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(orderId);
        dataOutput.writeUTF(pId);
        dataOutput.writeDouble(price);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        orderId = dataInput.readUTF();
        pId = dataInput.readUTF();
        price = dataInput.readDouble();
    }

    @Override
    public String toString() {
        return  orderId + '\t' + pId + '\t'  + price;
    }
}
