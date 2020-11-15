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
package bo.gob.sin.sre.fac.cfec.dto.interfaces;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class RespuestaAdapter extends XmlAdapter<Respuesta, IRespuesta> {

	@Override
	public IRespuesta unmarshal(Respuesta mensaje) throws Exception {
		return mensaje;
	}

	@Override
	public Respuesta marshal(IRespuesta mensaje) throws Exception {
		if (mensaje instanceof Respuesta) {
			return (Respuesta) mensaje;
		}
		Respuesta impl = new Respuesta();
		impl.setCodigoRecepcion(mensaje.getCodigoRecepcion());
		impl.setTransaccion(mensaje.isTransaccion());
		impl.setCodigoEstado(mensaje.getCodigoEstado());
		impl.setListaCodigosRespuestas(mensaje.getListaCodigosRespuestas());
		impl.setListaErroresDetalles(mensaje.getListaErroresDetalles());
		return impl;
	}

}
