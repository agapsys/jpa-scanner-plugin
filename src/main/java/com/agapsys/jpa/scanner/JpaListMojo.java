/*
 * Copyright 2016 Agapsys Tecnologia Ltda-ME.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.agapsys.jpa.scanner;

import com.agapsys.mvn.scanner.AbstractListMojo;
import com.agapsys.mvn.scanner.ScannerDefs;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

/**
 * JPA implementation of AbstractListMojo
 * @author Leandro Oliveira (leandro@agapsys.com)
 */
@Mojo(name = "list", defaultPhase = LifecyclePhase.GENERATE_RESOURCES, requiresDependencyResolution = ResolutionScope.COMPILE)
public class JpaListMojo extends AbstractListMojo {

	@Parameter(property = "project", readonly = true)
	private MavenProject mavenProject;
	
	@Override
	protected MavenProject getMavenProject() {
		return mavenProject;
	}

	@Override
	protected String getFilterPropertyName() {
		return "jpa-classes";
	}

	@Override
	protected String getExposedEntry(String scanInfoEntry) {
		return String.format("<class>%s</class>", scanInfoEntry);
	}

	@Override
	protected ScannerDefs getScannerDefs() {
		return JpaScannerDefs.getInstance();
	}
	
	@Parameter(defaultValue = "false", name = "include-dependencies")
	private boolean includeDependencies;

	@Override
	protected boolean includeDependencies() {
		return includeDependencies;
	}
	
	@Parameter(defaultValue = "false", name = "include-tests")
	private boolean includeTests;

	@Override
	protected boolean includeTests() {
		return includeTests;
	}
}