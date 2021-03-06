/*
 * Copyright 2020 Confluent Inc.
 *
 * Licensed under the Confluent Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.ksql.api.client;

import io.confluent.ksql.rest.server.KsqlRestConfig;
import java.util.Map;
import org.apache.kafka.common.config.SslConfigs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientTlsMutualAuthTest extends ClientTlsTest {

  protected static final Logger log = LoggerFactory.getLogger(ClientTlsMutualAuthTest.class);

  @Override
  protected KsqlRestConfig createServerConfig() {
    final Map<String, Object> config = serverConfigWithTls();
    config.put(
        KsqlRestConfig.SSL_CLIENT_AUTHENTICATION_CONFIG,
        KsqlRestConfig.SSL_CLIENT_AUTHENTICATION_REQUIRED
    );
    config.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, TRUST_STORE_PATH);
    config.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, TRUST_STORE_PASSWORD);
    return new KsqlRestConfig(config);
  }

  @Override
  protected ClientOptions createJavaClientOptions() {
    return super.createJavaClientOptions()
        .setKeyStore(KEY_STORE_PATH)
        .setKeyStorePassword(KEY_STORE_PASSWORD);
  }

}
