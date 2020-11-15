/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package bo.gob.sin.sre.fac.cfec.dto.comp.interfaces;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class SolicitudRecepcionAdapter
		extends XmlAdapter<SolicitudRecepcion, ISolicitudRecepcion> {

	@Override
	public ISolicitudRecepcion unmarshal(SolicitudRecepcion mensaje) throws Exception {
		return mensaje;
	}

	@Override
	public SolicitudRecepcion marshal(ISolicitudRecepcion mensaje) throws Exception {
		if (mensaje instanceof SolicitudRecepcion) {
			return (SolicitudRecepcion) mensaje;
		}
		SolicitudRecepcion impl = new SolicitudRecepcion();

		impl.setCodigoSistema(mensaje.getCodigoSistema());

		impl.setCodigoAmbiente(mensaje.getCodigoAmbiente());

		impl.setNit(mensaje.getNit());

		impl.setCuis(mensaje.getCuis());

		impl.setCufd(mensaje.getCufd());

		impl.setCodigoSucursal(mensaje.getCodigoSucursal());

		impl.setCodigoPuntoVenta(mensaje.getCodigoPuntoVenta());

		impl.setFechaEnvio(mensaje.getFechaEnvio());

		impl.setArchivo(mensaje.getArchivo());

		impl.setHashArchivo(mensaje.getHashArchivo());
		
		return impl;
	}

}
