package com.jmoiron.ulvcovm.data.covers;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.cover.CoverDefinition;
import com.gregtechceu.gtceu.common.cover.*;
import com.jmoiron.ulvcovm.UCMCore;
import com.gregtechceu.gtceu.client.renderer.cover.*;

import java.util.ArrayList;
import java.util.List;

public class Covers {

    public static final List<CoverDefinition> ALL_COVERS = new ArrayList<>(4);

    public static CoverDefinition ULV_CONVEYOR = registerULV(
        "conveyor",
        ConveyorCover::new,
        ConveyorCoverRenderer.INSTANCE
    );

    public static CoverDefinition ULV_PUMP = register(
        "pump",
        (def, cov, side) -> new PumpCover(def, cov, side, GTValues.ULV, 32),
        PumpCoverRenderer.INSTANCE
    );

    public static CoverDefinition ULV_ROBOT_ARM = registerULV(
        "robot_arm",
        RobotArmCover::new,
        RobotArmCoverRenderer.INSTANCE
    );

    public static CoverDefinition ULV_FLUID_REGULATOR = register(
        "fluid_regulator",
        (def, cov, side) -> new FluidRegulatorCover(def, cov, side, GTValues.ULV, 32),
        FluidRegulatorCoverRenderer.INSTANCE
    );

    public static CoverDefinition registerULV(
            String id,
            CoverDefinition.TieredCoverBehaviourProvider behavior,
            ICoverRenderer renderer
    ) {
        return register(
                id,
                (def, cov, side) -> behavior.create(def, cov, side, GTValues.ULV),
                renderer);
    }

    public static CoverDefinition register(
            String id,
            CoverDefinition.CoverBehaviourProvider behavior,
            ICoverRenderer renderer
        ) {

        var definition = new CoverDefinition(UCMCore.id(id), behavior, renderer);
        ALL_COVERS.add(definition);
        return definition;
    }
}
