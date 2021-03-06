package org.apache.solr.security;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MockAuthorizationPlugin implements AuthorizationPlugin{

  private Logger log = LoggerFactory.getLogger(MockAuthorizationPlugin.class);
  HashSet<String> denyUsers;
  
  @Override
  public AuthorizationResponse authorize(AuthorizationContext context) {
    log.info("User request: " + context.getParams().get("uname"));
    if(denyUsers.contains(context.getParams().get("uname")))
      return new AuthorizationResponse(403);
    else
      return new AuthorizationResponse(200);
  }

  @Override
  public void init(Map<String, Object> initInfo) {
    denyUsers = new HashSet();
    denyUsers.add("user1");
    denyUsers.add("user2");
  }

  @Override
  public void close() throws IOException {

  }
}