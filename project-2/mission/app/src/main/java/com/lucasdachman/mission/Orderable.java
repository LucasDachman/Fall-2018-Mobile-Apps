package com.lucasdachman.mission;

import java.util.ArrayList;

public class Orderable implements Comparable<Orderable> {
    float order;
    public float getOrder() {
        return order;
    }

    public void setOrder(float order) {
        this.order = order;
    }

    @Override
    public int compareTo(Orderable o) {
        return this.getOrder() > o.getOrder() ? 1
                : this.getOrder() < o.getOrder() ? -1
                : 0;
    }

    public static void setOrder(Orderable item, ArrayList<? extends Orderable> list) {
        if (list.size() > 0) {
            item.setOrder(list.get(0).getOrder() - 1);
        } else {
            item.setOrder(0);
        }
    }
}
