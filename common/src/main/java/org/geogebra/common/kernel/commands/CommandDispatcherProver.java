package org.geogebra.common.kernel.commands;

import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.advanced.CmdAreCollinear;
import org.geogebra.common.kernel.advanced.CmdAreConcurrent;
import org.geogebra.common.kernel.advanced.CmdAreConcyclic;
import org.geogebra.common.kernel.advanced.CmdAreCongruent;
import org.geogebra.common.kernel.advanced.CmdAreEqual;
import org.geogebra.common.kernel.advanced.CmdAreParallel;
import org.geogebra.common.kernel.advanced.CmdArePerpendicular;
import org.geogebra.common.kernel.advanced.CmdEnvelope;
import org.geogebra.common.kernel.advanced.CmdIsTangent;
import org.geogebra.common.kernel.advanced.CmdLocusEquation;
import org.geogebra.common.kernel.advanced.CmdProve;
import org.geogebra.common.kernel.advanced.CmdProveDetails;

/**
 * Dispatcher for prover commands
 */
public class CommandDispatcherProver implements CommandDispatcherInterface {

    @Override
    public CommandProcessor dispatch(Commands c, Kernel kernel) {
	    return switch (c) {
		    case Prove -> new CmdProve(kernel);
		    case ProveDetails -> new CmdProveDetails(kernel);
		    case AreCollinear -> new CmdAreCollinear(kernel);
		    case IsTangent -> new CmdIsTangent(kernel);
		    case AreParallel -> new CmdAreParallel(kernel);
		    case AreConcyclic -> new CmdAreConcyclic(kernel);
		    case ArePerpendicular -> new CmdArePerpendicular(kernel);
		    case AreEqual -> new CmdAreEqual(kernel);
		    case AreCongruent -> new CmdAreCongruent(kernel);
		    case AreConcurrent -> new CmdAreConcurrent(kernel);
		    case LocusEquation -> new CmdLocusEquation(kernel);
		    case Envelope -> new CmdEnvelope(kernel);
		    default -> null;
	    };
    }
}
