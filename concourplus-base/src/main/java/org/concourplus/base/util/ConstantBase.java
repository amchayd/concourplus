package org.concourplus.base.util;

import java.math.MathContext;
import java.math.RoundingMode;

public interface ConstantBase {

	String DATE_FORMAT = "dd/MM/yyyy";

	String FORMULAIRE_LAYOUT = "formLayout";

	String DEFAULT_MESSAGE = "default Message";

	MathContext DECIMAL_4 = new MathContext(4, RoundingMode.HALF_EVEN);

}
