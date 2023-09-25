import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main {
	public static void main(String[] args) throws Exception {
		Object inputData = new ObjectInputStream(Main.class.getResourceAsStream("/input.dat")).readObject();

		byte[] hessianSerializedData = serializeWithHessian(inputData);

		Object result = deserializeWithHessian(hessianSerializedData);

		System.out.println(result);
	}

	private static byte[] serializeWithHessian(Object inputData) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Hessian2Output output = new Hessian2Output(baos);
		output.writeObject(inputData);
		output.flush();
		output.close();

		return baos.toByteArray();
	}

	private static Object deserializeWithHessian(byte[] hessianSerializedData) throws IOException {
		Hessian2Input hessian2Input = new Hessian2Input(new ByteArrayInputStream(hessianSerializedData));
		return hessian2Input.readObject();
	}
}