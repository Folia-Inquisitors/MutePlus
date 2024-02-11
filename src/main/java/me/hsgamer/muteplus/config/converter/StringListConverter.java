package me.hsgamer.muteplus.config.converter;

import me.hsgamer.hscore.common.CollectionUtils;
import me.hsgamer.hscore.config.annotation.converter.Converter;

public class StringListConverter implements Converter {
    @Override
    public Object convert(Object raw) {
        if (raw == null) return null;
        return CollectionUtils.createStringListFromObject(raw);
    }

    @Override
    public Object convertToRaw(Object value) {
        return value;
    }
}
