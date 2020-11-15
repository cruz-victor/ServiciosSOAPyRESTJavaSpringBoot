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

public class ObtencionPorDiaComprasAdapter
		extends XmlAdapter<ObtencionPorDiaCompras, IObtencionPorDiaCompras> {

	@Override
	public IObtencionPorDiaCompras unmarshal(ObtencionPorDiaCompras mensaje) throws Exception {
		return mensaje;
	}

	@Override
	public ObtencionPorDiaCompras marshal(IObtencionPorDiaCompras mensaje) throws Exception {
		if (mensaje instanceof ObtencionPorDiaCompras) {
			return (ObtencionPorDiaCompras) mensaje;
		}
		ObtencionPorDiaCompras impl = new ObtencionPorDiaCompras();
		impl.setFecha(mensaje.getFecha());
		impl.setNit(mensaje.getNit());
		impl.setCodigoSistema(mensaje.getCodigoSistema());
		impl.setCufd(mensaje.getCufd());
		impl.setCuis(mensaje.getCuis());
		impl.setCodigoSucursal(mensaje.getCodigoSucursal());
		impl.setCodigoPuntoVenta(mensaje.getCodigoPuntoVenta());
		impl.setCodigoAmbiente(mensaje.getCodigoAmbiente());

		return impl;
	}

}
