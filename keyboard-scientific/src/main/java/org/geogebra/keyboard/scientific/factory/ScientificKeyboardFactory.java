package org.geogebra.keyboard.scientific.factory;

import org.geogebra.keyboard.base.KeyboardFactory;
import org.geogebra.keyboard.base.model.impl.factory.CommonKeyboardFactory;
import org.geogebra.keyboard.scientific.model.ScientificDefaultKeyboardFactory;
import org.geogebra.keyboard.scientific.model.ScientificFunctionKeyboardFactory;
import org.geogebra.keyboard.scientific.model.ScientificLettersKeyboardFactory;

public class ScientificKeyboardFactory extends CommonKeyboardFactory {

    public static final KeyboardFactory INSTANCE = new ScientificKeyboardFactory();

    /**
     * Creates a KeyboardFactory with default implementations
     * for keyboard model factories.
     */
    private ScientificKeyboardFactory() {
        mathKeyboardFactory = new ScientificDefaultKeyboardFactory(true);
        defaultKeyboardModelFactory = new ScientificDefaultKeyboardFactory(false);
        functionKeyboardFactory = new ScientificFunctionKeyboardFactory();
        letterKeyboardFactory = new ScientificLettersKeyboardFactory();
    }
}
