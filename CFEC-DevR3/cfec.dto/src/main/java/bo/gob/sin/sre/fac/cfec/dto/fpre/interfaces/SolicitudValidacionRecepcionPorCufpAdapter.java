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
package bo.gob.sin.sre.fac.cfec.dto.fpre.interfaces;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class SolicitudValidacionRecepcionPorCufpAdapter
		extends XmlAdapter<SolicitudValidacionRecepcionPorCufp, ISolicitudValidacionRecepcionPorCufp> {

	@Override
	public ISolicitudValidacionRecepcionPorCufp unmarshal(SolicitudValidacionRecepcionPorCufp mensaje)
			throws Exception {
		return mensaje;
	}

	@Override
	public SolicitudValidacionRecepcionPorCufp marshal(ISolicitudValidacionRecepcionPorCufp mensaje) throws Exception {
		if (mensaje instanceof SolicitudValidacionRecepcion) {
			return (SolicitudValidacionRecepcionPorCufp) mensaje;
		}
		SolicitudValidacionRecepcionPorCufp impl = new SolicitudValidacionRecepcionPorCufp();

		impl.setCodigoSistema(mensaje.getCodigoSistema());

		impl.setCodigoAmbiente(mensaje.getCodigoAmbiente());

		impl.setCodigoEmision(mensaje.getCodigoEmision());

		impl.setCodigoModalidad(mensaje.getCodigoModalidad());

		impl.setNit(mensaje.getNit());

		impl.setCuis(mensaje.getCuis());

		impl.setCufd(mensaje.getCufd());

		impl.setCuape(mensaje.getCuape());

		impl.setCodigoDocumentoFiscal(mensaje.getCodigoDocumentoFiscal());

		impl.setCodigoDocumentoSector(mensaje.getCodigoDocumentoSector());

		impl.setCodigoSucursal(mensaje.getCodigoSucursal());

		impl.setCodigoPuntoVenta(mensaje.getCodigoPuntoVenta());

		impl.setCufp(mensaje.getCufp());

		return impl;
	}

}