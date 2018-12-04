package com.sobaka.sort.chunk.creator;

import com.sobaka.sort.chunk.GuavaSortedChunk;
import com.sobaka.sort.chunk.SortedChunk;

import java.util.concurrent.atomic.AtomicInteger;

public class GuavaUniqueChunkCreator implements ChunkCreator {
    private AtomicInteger number = new AtomicInteger(0);

    @Override
    public SortedChunk createSortedChunk() {
        number.incrementAndGet();
        return new GuavaSortedChunk("chunck" + number);
    }
}
