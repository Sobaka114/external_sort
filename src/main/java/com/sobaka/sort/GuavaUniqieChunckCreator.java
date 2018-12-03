package com.sobaka.sort;

import java.util.concurrent.atomic.AtomicInteger;

public class GuavaUniqieChunckCreator implements ChunkCreator {
    private AtomicInteger number = new AtomicInteger(0);

    @Override
    public SortedChunck createSortedChunk() {
        number.incrementAndGet();
        return new GuavaSortedChunk("chunck" + number);
    }
}
