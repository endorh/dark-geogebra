/* MhchemArrowConsumer.java
 * =========================================================================
 * This file is part of the JLaTeXMath Library - http://forge.scilab.org/jlatexmath
 *
 * Copyright (C) 2018 DENIZET Calixte
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * A copy of the GNU General Public License can be found in the file
 * LICENSE.txt provided with the source distribution of this program (see
 * the META-INF directory in the source jar). This license can also be
 * found on the GNU website at http://www.gnu.org/licenses/gpl.html.
 *
 * If you did not receive a copy of the GNU General Public License along
 * with this program, contact the lead developer, or write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 *
 * Linking this library statically or dynamically with other modules
 * is making a combined work based on this library. Thus, the terms
 * and conditions of the GNU General Public License cover the whole
 * combination.
 *
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce
 * an executable, regardless of the license terms of these independent
 * modules, and to copy and distribute the resulting executable under terms
 * of your choice, provided that you also meet, for each linked independent
 * module, the terms and conditions of the license of that module.
 * An independent module is a module which is not derived from or based
 * on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obliged to do so.
 * If you do not wish to do so, delete this exception statement from your
 * version.
 *
 */

package com.himamis.retex.renderer.share.mhchem;

import com.himamis.retex.renderer.share.Atom;
import com.himamis.retex.renderer.share.AtomConsumer;
import com.himamis.retex.renderer.share.EmptyAtom;
import com.himamis.retex.renderer.share.GroupConsumer;
import com.himamis.retex.renderer.share.RowAtom;
import com.himamis.retex.renderer.share.TeXConstants;
import com.himamis.retex.renderer.share.TeXLength;
import com.himamis.retex.renderer.share.TeXParser;
import com.himamis.retex.renderer.share.Unit;
import com.himamis.retex.renderer.share.XArrowAtom;

public class MhchemArrowConsumer implements AtomConsumer {

	private final MhchemParser.Arrow arrow;
	private Atom sup;
	private Atom sub;

	public MhchemArrowConsumer(MhchemParser.Arrow arrow) {
		this.arrow = arrow;
	}

	@Override
	public boolean init(TeXParser tp) {
		if (tp.hasOptionNoWhites()) {
			tp.addConsumer(this);
			tp.addConsumer(new GroupConsumer(TeXConstants.Opener.LSQBRACKET));
		} else {
			tp.addToConsumer(get());
		}
		return false;
	}

	@Override
	public void add(TeXParser tp, Atom a) {
		if (sup == null) {
			sup = a;
			if (tp.hasOptionNoWhites()) {
				tp.addConsumer(
						new GroupConsumer(TeXConstants.Opener.LSQBRACKET));
				return;
			}
		} else {
			sub = a;
		}
		tp.closeConsumer(get());
	}

	public Atom get() {
		final Atom top = sup == null ? EmptyAtom.get() : sup;
		final Atom bot = sub == null ? EmptyAtom.get() : sub;
		final TeXLength minW = new TeXLength(Unit.EM, 2.);

		return switch (arrow) {
			case left -> // <-
					new XArrowAtom(top, bot, minW, XArrowAtom.Kind.Left);
			case right -> // ->
					new XArrowAtom(top, bot, minW, XArrowAtom.Kind.Right);
			case leftright -> // <->
					new XArrowAtom(top, bot, minW, XArrowAtom.Kind.LR);
			case LeftRight -> // <-->
					new XArrowAtom(top, bot, minW, XArrowAtom.Kind.RightAndLeft);
			case leftrightHarpoon -> // <=>
					new XArrowAtom(top, bot, minW,
							XArrowAtom.Kind.RightLeftHarpoons);
			case leftrightSmallHarpoon -> // <=>>
					new XArrowAtom(top, bot, minW,
							XArrowAtom.Kind.RightSmallLeftHarpoons);
			case leftSmallHarpoonRight -> // <<=>
					new XArrowAtom(top, bot, minW,
							XArrowAtom.Kind.SmallRightLeftHarpoons);
		};

	}

	@Override
	public Atom getLastAtom() {
		return null;
	}

	@Override
	public boolean close(TeXParser tp) {
		tp.closeConsumer(get());
		return true;
	}

	@Override
	public boolean isClosable() {
		return true;
	}

	@Override
	public RowAtom steal(TeXParser tp) {
		close(tp);
		return tp.steal();
	}

	@Override
	public boolean isArray() {
		return false;
	}

	@Override
	public boolean isAmpersandAllowed() {
		return false;
	}

	@Override
	public boolean isHandlingArg() {
		return false;
	}

	@Override
	public void lbrace(TeXParser tp) {
	}

	@Override
	public void rbrace(TeXParser tp) {
	}
}
