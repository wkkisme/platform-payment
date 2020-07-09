package com.platfrom.payment.domain.core;

import com.platfrom.payment.common.util.SnowFlakeWorker;
import org.springframework.stereotype.Component;

@Component
public class SequenceService {

    public long getNextId() {
        return SnowFlakeWorker.nextId();
    }


    public String getNextStr() {
        return String.format("%020d",SnowFlakeWorker.nextId());
    }


    public static void main(String[] args) {
        System.out.println(String.format("%020d",SnowFlakeWorker.nextId()));
    }
}
