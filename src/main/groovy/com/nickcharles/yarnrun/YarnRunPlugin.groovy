/*
 * Copyright 2016 Palantir Technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * <http://www.apache.org/licenses/LICENSE-2.0>
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nickcharles.yarnrun

import com.moowork.gradle.node.NodePlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

class YarnRunPlugin implements Plugin<Project> {

    public static final String EXTENSION_NAME = "yarnRun"
    public static final String GROUP_NAME = "Yarn Run"

    @Override
    void apply(Project project) {

        project.plugins.apply(NodePlugin.class)

        YarnRunExtension extension = project.extensions.create(EXTENSION_NAME, YarnRunExtension)

        project.afterEvaluate {
            project.task("clean") {
                group = GROUP_NAME
                description = "Runs 'yarn run clean'"

                dependsOn "yarn"
                dependsOn "yarn_run_${extension.clean}"

                mustRunAfter "yarn"
            }

            project.task("test") {
                group = GROUP_NAME
                description = "Runs 'yarn run test'"

                dependsOn "yarn"
                dependsOn "yarn_run_${extension.test}"

                mustRunAfter "yarn"
                mustRunAfter "clean"
            }

            project.task("check") {
                group = GROUP_NAME
                description = "Depends on ':test'"

                dependsOn "test"
            }

            project.task("build") {
                group = GROUP_NAME
                description = "Runs 'yarn run build' and depends on ':check'"

                dependsOn "yarn"
                dependsOn "check"
                dependsOn "yarn_run_${extension.build}"

                mustRunAfter "yarn"
                mustRunAfter "clean"
                mustRunAfter "check"
            }

            project.task("buildDev") {
                group = GROUP_NAME
                description = "Runs 'yarn run buildDev' and depends on ':check'"

                dependsOn "yarn"
                dependsOn "check"
                dependsOn "yarn_run_${extension.buildDev}"

                mustRunAfter "yarn"
                mustRunAfter "clean"
                mustRunAfter "check"
            }
        }
    }
}
