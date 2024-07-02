package org.jp441.mymediatracker.covers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameCover {
    private int id;
    private String name;
    private String coverURL;
}
