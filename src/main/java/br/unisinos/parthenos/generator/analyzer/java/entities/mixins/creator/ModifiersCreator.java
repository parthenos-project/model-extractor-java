package br.unisinos.parthenos.generator.analyzer.java.entities.mixins.creator;

import br.unisinos.parthenos.generator.enumerator.java.JavaEdgeLabel;
import br.unisinos.parthenos.generator.enumerator.java.JavaVertexDescriptor;
import br.unisinos.parthenos.generator.prolog.fact.Edge;
import br.unisinos.parthenos.generator.prolog.fact.Fact;
import br.unisinos.parthenos.generator.prolog.fact.Vertex;
import br.unisinos.parthenos.generator.prolog.term.Atom;
import com.github.javaparser.ast.Modifier;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface ModifiersCreator {
  List<Modifier> getModifiers();

  default Vertex createModifierVertex(Modifier modifier) {
    final Atom modifierAtom = new Atom(modifier.getKeyword().asString());
    return new Vertex(JavaVertexDescriptor.MODIFIER, modifierAtom);
  }

  default Edge createModifierEdge(Vertex vertex, Vertex modifierVertex) {
    return new Edge(vertex.getLabel(), JavaEdgeLabel.MODIFIER, modifierVertex.getLabel());
  }

  default Set<Fact> createModifiersFacts(Vertex vertex) {
    final Set<Fact> modifierFacts = new HashSet<>();

    for (Modifier modifier : this.getModifiers()) {
      final Vertex modifierVertex = this.createModifierVertex(modifier);

      modifierFacts.add(modifierVertex);
      modifierFacts.add(this.createModifierEdge(vertex, modifierVertex));
    }

    return modifierFacts;
  }
}
