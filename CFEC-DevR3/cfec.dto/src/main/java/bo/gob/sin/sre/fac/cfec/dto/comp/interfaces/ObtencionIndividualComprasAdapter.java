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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ObtencionIndividualComprasAdapter
		extends XmlAdapter<ObtencionIndividualCompras, IObtencionIndividualCompras> {

	@Override
	public IObtencionIndividualCompras unmarshal(ObtencionIndividualCompras mensaje) throws Exception {
		return mensaje;
	}

	@Override
	public ObtencionIndividualCompras marshal(IObtencionIndividualCompras mensaje) throws Exception {
		if (mensaje instanceof ObtencionIndividualCompras) {
			return (ObtencionIndividualCompras) mensaje;
		}
		ObtencionIndividualCompras impl = new ObtencionIndividualCompras();

		impl.setCuf(mensaje.getCuf());
		impl.setNit(mensaje.getNit());
		impl.setNumeroFactura(mensaje.getNumeroFactura());
		impl.setCodigoSistema(mensaje.getCodigoSistema());
		impl.setCufd(mensaje.getCufd());
		impl.setCuis(mensaje.getCuis());
		impl.setCodigoSucursal(mensaje.getCodigoSucursal());
		impl.setCodigoPuntoVenta(mensaje.getCodigoPuntoVenta());
		impl.setCodigoAmbiente(mensaje.getCodigoAmbiente());

		return impl;
	}

}
