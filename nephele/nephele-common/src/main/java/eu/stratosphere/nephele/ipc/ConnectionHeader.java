/***********************************************************************************************************************
 *
 * Copyright (C) 2010 by the Stratosphere project (http://stratosphere.eu)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 **********************************************************************************************************************/

package eu.stratosphere.nephele.ipc;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import eu.stratosphere.nephele.io.IOReadableWritable;
import eu.stratosphere.nephele.types.StringRecord;

/**
 * The IPC connection header sent by the client to the server
 * on connection establishment.
 */
class ConnectionHeader implements IOReadableWritable {

	private String protocol;

	public ConnectionHeader() {
	}

	/**
	 * Create a new {@link ConnectionHeader} with the given <code>protocol</code> and {@link UserGroupInformation}.
	 * 
	 * @param protocol
	 *        protocol used for communication between the IPC client
	 *        and the server
	 * @param ugi
	 *        {@link UserGroupInformation} of the client communicating with
	 *        the server
	 */
	public ConnectionHeader(String protocol) {
		this.protocol = protocol;
	}

	@Override
	public void read(DataInput in) throws IOException {
		protocol = StringRecord.readString(in);
		if (protocol.isEmpty()) {
			protocol = null;
		}
	}

	@Override
	public void write(DataOutput out) throws IOException {
		StringRecord.writeString(out, (protocol == null) ? "" : protocol);
	}

	public String getProtocol() {
		return protocol;
	}

	public String toString() {
		return protocol;
	}
}
