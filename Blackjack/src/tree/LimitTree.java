package tree;

public class LimitTree {

	private Node root;

	public LimitTree() {
		this.root = null;
	}

	public void add(double trials, int limit) {
		if (root == null) {
			root = new Node(trials, limit);
		} else {
			root.addNode(trials, limit);
		}
	}

	public int maxLimit() {
		return root.getMaximum().limit;
	}

	public double maxTrials() {
		return root.getMaximum().trials;
	}

	public int minLimit() {
		return root.getMinimum().limit;
	}

	public double minTrials() {
		return root.getMinimum().trials;
	}

	private class Node {
		double trials;
		int limit;

		Node right;
		Node left;

		Node(double trials, int limit) {
			this.trials = trials;
			this.limit = limit;
		}

		void addNode(double trials, int limit) {
			if (trials > this.trials) {
				if (right == null) {
					this.right = new Node(trials, limit);
				} else {
					right.addNode(trials, limit);
				}
			} else {
				if (left == null) {
					this.left = new Node(trials, limit);
				} else {
					left.addNode(trials, limit);
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
