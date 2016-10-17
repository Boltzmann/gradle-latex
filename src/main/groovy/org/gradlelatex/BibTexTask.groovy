package org.gradlelatex

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

/**
 * Gradle task to run bibTex on an aux file
 */
class BibTexTask extends DefaultTask {
  final String group = 'Latex'
  final String description = 'Runs bibtex on all *.bib files in project'

  LatexObj obj

  @InputFile
  File bib

  @OutputFile
  File bbl

  @TaskAction
  void bibtex() {
    project.latex.with(obj.name) { LatexObj subObj ->
      project.latex.utils.pdfLatex(subObj)
    }
    project.latex.utils.bibTex(obj)
  }

  void setObj(LatexObj obj) {
    this.obj = obj
    this.bib = obj.bib
    this.bbl = new File(project.latex.auxDir, "${obj.name}.bbl")
  }
}