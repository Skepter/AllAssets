package io.github.skepter.allassets.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectionUtils {

	public static Object getPerfectField(final Object object, final Class<?> clazz, final String fieldName) {
		try {
			for (Field f : clazz.getFields())
				if (f.getName().toLowerCase().contains(fieldName.toLowerCase()))
					return ReflectionUtils.getPrivateFieldValue(object, f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void setPerfectField(final Object object, final Class<?> clazz, final String fieldName, final Object value) {
		try {
			for (Field f : clazz.getFields())
				if (f.getName().toLowerCase().contains(fieldName.toLowerCase())) {
					f.setAccessible(true);
					f.set(object, value);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Retrieves an Enumeration via Reflection */
	public static Object getEnum(final Class<?> enumClass, final String enumName) throws NullPointerException {
		for (final Object object : enumClass.getEnumConstants())
			if (object.toString().equals(enumName))
				return object;
		throw new NullPointerException();
	}

	/** Return the private field */
	public static Field getPrivateField(final Object object, final String fieldName) throws Exception {
		final Field field = object.getClass().getDeclaredField(fieldName);
		return field;
	}

	/** Return the value from a private field */
	public static Object getPrivateFieldValue(final Object object, final String fieldName) throws Exception {
		final Field field = object.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(object);
	}

	/** Return the value from a private field */
	public static Object getPrivateFieldValue(final Object object, final Field field) throws Exception {
		field.setAccessible(true);
		return field.get(object);
	}

	/** Return the value from a non private field */
	public static Object getFieldValue(final Object object, final String fieldName) throws Exception {
		return object.getClass().getDeclaredField(fieldName).get(object);
	}

	/** Sets the value of a private field */
	public static void setPrivateField(final Object object, final String fieldName, final Object data) throws Exception {
		final Field field = object.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(object, data);
	}

	/** Sets the value of a final static field */
	public static void setFinalStaticField(Field field, Object data) throws Exception {
		field.setAccessible(true);

		final Field modifiersField = Field.class.getDeclaredField("modifiers");
		modifiersField.setAccessible(true);
		modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

		field.set(null, data);
	}

}