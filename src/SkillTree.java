/**
 * the skill tree that will store skill nodes that enhance player ability
 */
public class SkillTree 
{
    private SkillNode root;


    public SkillTree(SkillNode root)
    {
        this.root = root;
    }

    public boolean hasNode(SkillNode node)
    {
        return root.contains(node);
    }

    public boolean hasSkill(Skill skill)
    {
        return findNode(root, skill) != null;
    }

    /**
     * Claude did this
     * finds the node in the tree that holds the given skill
     * @param skill the skill to search for
     * @return the SkillNode containing the skill, or null if the skill is not in the tree
     */
    public SkillNode findNode(Skill skill)
    {
        return findNode(root, skill);
    }

    /**
     * Claude did this
     * recursive helper that checks the current node, then searches each of its children
     * @param current the node being checked
     * @param skill the skill to search for
     * @return the SkillNode containing the skill, or null if it is not under current
     */
    private SkillNode findNode(SkillNode current, Skill skill)
    {
        if (current == null)
        {
            return null;
        }

        if (current.getSkill() == skill)
        {
            return current;
        }

        for (SkillNode child : current.getChildren())
        {
            SkillNode found = findNode(child, skill);
            if (found != null)
            {
                return found;
            }
        }
        return null;
    }

    public SkillNode getRoot()
    {
        return root;
    }

    public void setRoot(SkillNode root)
    {
        this.root = root;
    }

}
