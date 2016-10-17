package org.gradlelatex

import org.gradle.api.Project
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class LatexUtils {
  Logger LOG = LoggerFactory.getLogger(LatexUtils)
  
  final Project p

  LatexUtils(Project p) {
    this.p = p
  }

  void exec(String cmd) {
    LOG.quiet "Executing $cmd"
    def cmdSplit = cmd.split(' ')
    p.ant.exec(executable: cmdSplit[0], dir: p.projectDir) {
      cmdSplit[1..-1].each { argv ->
        arg(value: argv)
      }
    }
  }
  
  void emptyContent(File dir) {
    LOG.quiet "Emptying content from folder $dir"
    p.ant.delete {
      fileset(dir: dir, includes:'**/*')
    }
  }
}
