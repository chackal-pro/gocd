/*
 * Copyright 2023 Thoughtworks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.thoughtworks.go.apiv1.shared.representers.materials;

import com.thoughtworks.go.api.base.OutputWriter;
import com.thoughtworks.go.api.representers.JsonReader;
import com.thoughtworks.go.config.materials.dependency.DependencyMaterialConfig;

public class DependencyMaterialRepresenter {

    public static void toJSON(OutputWriter jsonWriter, DependencyMaterialConfig dependencyMaterialConfig) {
        jsonWriter.add("pipeline", dependencyMaterialConfig.getPipelineName());
        jsonWriter.add("stage", dependencyMaterialConfig.getStageName());
        jsonWriter.add("name", dependencyMaterialConfig.getNameWithoutDefaults());
        jsonWriter.add("auto_update", dependencyMaterialConfig.isAutoUpdate());
    }

    public static DependencyMaterialConfig fromJSON(JsonReader jsonReader) {
        DependencyMaterialConfig dependencyMaterialConfig = new DependencyMaterialConfig();
        jsonReader.readCaseInsensitiveStringIfPresent("pipeline", dependencyMaterialConfig::setPipelineName);
        jsonReader.readCaseInsensitiveStringIfPresent("stage", dependencyMaterialConfig::setStageName);
        jsonReader.readCaseInsensitiveStringIfPresent("name", dependencyMaterialConfig::setName);
        jsonReader.optBoolean("auto_update").ifPresent(dependencyMaterialConfig::setAutoUpdate);
        return dependencyMaterialConfig;
    }
}
