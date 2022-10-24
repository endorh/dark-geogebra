package org.geogebra.common.geogebra3D.kernel3D.commands;

import org.geogebra.common.geogebra3D.kernel3D.scripting.CmdSetSpinSpeed;
import org.geogebra.common.geogebra3D.kernel3D.scripting.CmdSetViewDirection;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.arithmetic.Command;
import org.geogebra.common.kernel.commands.CommandDispatcher;
import org.geogebra.common.kernel.commands.CommandDispatcherInterface;
import org.geogebra.common.kernel.commands.CommandProcessor;
import org.geogebra.common.kernel.commands.Commands;
import org.geogebra.common.kernel.kernelND.GeoConicNDConstants;
import org.geogebra.common.util.debug.Log;

/**
 * Command dispatcher for 3D
 *
 * @author Mathieu
 *
 */
public abstract class CommandDispatcher3D extends CommandDispatcher {

	/** dispatcher for 3D commands */
	protected static CommandDispatcherInterface commands3DDispatcher = null;

	private CommandDispatcher commandDispatcher;

	/**
	 * @param kernel
	 *            kernel
	 */
	public CommandDispatcher3D(Kernel kernel) {
		super(kernel);
		commandDispatcher = kernel.getApplication().newCommandDispatcher(kernel);
	}

	@Override
	public CommandProcessor commandTableSwitch(Command c) {
		String cmdName = c.getName();
		try {
			Commands command = Commands.valueOf(cmdName);
			if (!isAllowedByNameFilter(command)) {
				Log.info("The command is not allowed by the command filter");
				return null;
			}
			return switch (command) {
				case Segment -> new CmdSegment3D(kernel);
				case Line -> new CmdLine3D(kernel);
				case Ray -> new CmdRay3D(kernel);
				case Polygon -> new CmdPolygon3D(kernel);
				case Area -> new CmdArea3D(kernel);
				case Polyline, PolyLine -> new CmdPolyLine3D(kernel);
				case Point -> new CmdPoint3D(kernel);
				case Midpoint, Center -> new CmdMidpoint3D(kernel);
				case Tangent -> new CmdTangent3D(kernel);
				case Polar -> new CmdPolar3D(kernel);
				case Diameter, ConjugateDiameter -> new CmdDiameter3D(kernel);
				case Circle -> new CmdCircle3D(kernel);
				case Ellipse -> new CmdEllipseHyperbola3D(kernel,
						GeoConicNDConstants.CONIC_ELLIPSE);
				case Hyperbola -> new CmdEllipseHyperbola3D(kernel,
						GeoConicNDConstants.CONIC_HYPERBOLA);
				case Conic -> new CmdConic3D(kernel);
				case CircumcircleSector, CircumcircularSector ->
						new CmdCircumcircleSector3D(kernel);
				case CircumcircleArc, CircumcircularArc -> new CmdCircumcircleArc3D(kernel);
				case Arc -> new CmdArcSector3D(kernel,
						GeoConicNDConstants.CONIC_PART_ARC);
				case Sector -> new CmdArcSector3D(kernel,
						GeoConicNDConstants.CONIC_PART_SECTOR);
				case CircleArc, CircularArc -> new CmdCircleArcSector3D(kernel,
						GeoConicNDConstants.CONIC_PART_ARC);
				case CircleSector, CircularSector -> new CmdCircleArcSector3D(kernel,
						GeoConicNDConstants.CONIC_PART_SECTOR);
				case Semicircle -> new CmdSemicircle3D(kernel);
				case Parabola -> new CmdParabola3D(kernel);
				case Corner -> new CmdCorner3D(kernel);
				case CornerThreeD -> new CmdVertexForce3D(kernel);
				case Locus -> new CmdLocus3D(kernel);
				case Vertex -> new CmdVertex3D(kernel);
				case FirstAxis -> new CmdAxis3D(kernel, 0);
				case SecondAxis -> new CmdAxis3D(kernel, 1);
				case Focus -> new CmdFocus3D(kernel);
				case PerpendicularLine, OrthogonalLine -> new CmdOrthogonalLine3D(kernel);
				case LineBisector, PerpendicularBisector -> new CmdLineBisector3D(kernel);
				case AngleBisector, AngularBisector -> new CmdAngularBisector3D(kernel);
				case PerpendicularVector, OrthogonalVector -> new CmdOrthogonalVector3D(kernel);
				case UnitPerpendicularVector, UnitOrthogonalVector ->
						new CmdUnitOrthogonalVector3D(kernel);
				case Direction -> new CmdUnitVector3D(kernel, false);
				case UnitVector -> new CmdUnitVector3D(kernel, true);
				case Curve, CurveCartesian -> new CmdCurveCartesian3D(kernel);
				case PointIn -> new CmdPointIn3D(kernel);
				case Distance -> new CmdDistance3D(kernel);
				case ClosestPoint -> new CmdClosestPoint3D(kernel);
				case ClosestPointRegion -> new CmdClosestPointRegion(kernel);
				case Intersect -> new CmdIntersect3D(kernel); // deprecated
				case IntersectPath, IntersectionPaths, IntersectRegion -> // deprecated
						new CmdIntersectPath3D(kernel);
				case IntersectCircle, IntersectConic -> new CmdIntersectConic(kernel);
				case Angle -> new CmdAngle3D(kernel);
				case InteriorAngles -> new CmdInteriorAngles3D(kernel);
				case Translate -> new CmdTranslate3D(kernel);
				case Rotate -> new CmdRotate3D(kernel);
				case Reflect, Mirror -> new CmdMirror3D(kernel);
				case Dilate -> new CmdDilate3D(kernel);
				case Length -> new CmdLength3D(kernel);
				case Volume -> new CmdVolume(kernel);
				case Height -> new CmdHeight(kernel);
				case Axes -> new CmdAxes3D(kernel);

				// scripting : 3D
				case SetViewDirection -> new CmdSetViewDirection(kernel);
				case SetSpinSpeed -> new CmdSetSpinSpeed(kernel);

				// polygon operations
				case Difference -> new CmdDifference3D(kernel);
				case Union -> new CmdUnion3D(kernel);

				// 3D commands dispatcher
				case Plane, PerpendicularPlane, OrthogonalPlane, PlaneBisector, Prism, Pyramid, Tetrahedron, Cube, Octahedron, Dodecahedron, Icosahedron, Polyhedron, Net, Sphere, Cone, InfiniteCone, ConeInfinite, Cylinder, InfiniteCylinder, CylinderInfinite, Side, QuadricSide, Bottom, Top, Ends ->
						get3DDispatcher().dispatch(command, kernel);
				default -> super.commandTableSwitch(c);
			};
		} catch (RuntimeException e) {
			Log.debug("command not found / CAS command called: " + cmdName);
		}
		return null;
	}

	// a hacky solution to avoid code repetition
	@Override
	public CommandDispatcherInterface getDiscreteDispatcher() {
		return commandDispatcher.getDiscreteDispatcher();
	}

	@Override
	public CommandDispatcherInterface getScriptingDispatcher() {
		return commandDispatcher.getScriptingDispatcher();
	}

	@Override
	public CommandDispatcherInterface getAdvancedDispatcher() {
		return commandDispatcher.getAdvancedDispatcher();
	}

	@Override
	public CommandDispatcherInterface getCASDispatcher() {
		return commandDispatcher.getCASDispatcher();
	}

	@Override
	public CommandDispatcherInterface getStatsDispatcher() {
		return commandDispatcher.getStatsDispatcher();
	}

	@Override
	public CommandDispatcherInterface getProverDispatcher() {
		return commandDispatcher.getProverDispatcher();
	}
}
