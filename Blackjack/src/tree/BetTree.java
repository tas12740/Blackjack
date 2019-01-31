package tree;

public class BetTree {
	private Node root;

	public BetTree() {
		this.root = null;
	}

	public void add(double trials, double percent) {
		if (root == null) {
			root = new Node(trials, percent);
		} else {
			root.addNode(trials, percent);
		}
	}

	public double maxTrials() {
		return root.getMaximum().trials;
	}

	public double maxPercent() {
		return root.getMaximum().percent;
	}

	public double minTrials() {
		return root.getMinimum().trials;
	}

	public double minPercent() {
		return root.getMinimum().percent;
	}

	private class Node {
		double trials;
		double percent;

		Node right;
		Node left;

		Node(double trials, double percent) {
			this.trials = trials;
			this.percent = percent;
		}

		void addNode(double trials, double percent) {
			if (trials > this.trials) {
				if (right == null) {
					this.right = new Node(trials, percent);
				} else {
					right.addNode(trials, percent);
				}
			} else {
				if (left == null) {
					this.left = new Node(trials, percent);
				} else {
					left.addNode(trials, percent);
				}
			}
		}

		Node getMaximum() {
			if (right == null) {
				return this;
			} else {
				return right.getMaximum();
			}
		}

		Node getMinimum() {
			if (left == null) {
				return this;
			} else {
				return left.getMinimum();
			}
		}
	}
}
