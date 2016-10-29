package com.open.lee.myeventbus;

/**
 * Created by Lee on 2016/10/29.
 */

public class EventType {
    Class<?> paramType;

    public EventType(Class<?> paramType){
        this.paramType = paramType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((paramType == null) ? 0 : paramType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        EventType other = (EventType) obj;
        if (paramType == null) {
            if (other.paramType != null)
                return false;
        } else if (!paramType.equals(other.paramType))
            return false;
        return true;
    }
}
